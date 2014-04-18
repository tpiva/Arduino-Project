package com.pos.agenda.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Agenda implements EntryPoint {
	
	// service
	private AgendaServiceAsync service = GWT.create(AgendaService.class);
	private AsyncCallback<ArrayList<Contato>> asyncContato;
	private AsyncDataProvider<Contato> dataProvider;
	private TextBox textoNome;
	private TextBox textoEmail;
	private TextBox textoTelefone;
	private TextBox textoCategoria;

	private List<Contato> listaContato;
	private CellTable<Contato> tabelaContatos;

	private int start = 0;
	private int length = 0;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		ServiceDefTarget target = (ServiceDefTarget) service;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "listar");
		
		criarLayout();
	}
	
	/* **************************************************************
	 * Métodos de layout
	 * **************************************************************
	 */
	private void criarLayout() {
		DockPanel dockPanel = new DockPanel();
	    dockPanel.setSpacing(20);
	    dockPanel.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
	    dockPanel.setWidth("100%");

	    // Add text all around
	    dockPanel.add(criarCampoBuscar(), DockPanel.NORTH);
	    dockPanel.add(criarCampoInserirAtualizar(), DockPanel.EAST);
	    dockPanel.add(criarTabela(), DockPanel.WEST);
	
		RootPanel.get().add(dockPanel);
	}

	private HorizontalPanel criarCampoBuscar() {
		final TextBox textoBuscar = new TextBox();
		Label labelNomeContato = new Label("Nome do contato:");
		Button botaoProcurar = new Button("Procurar");
		Button botatoTodosContatos = new Button("Todos os contatos");
		
		HorizontalPanel painelHorizontal = new HorizontalPanel();
		painelHorizontal.getElement().getStyle().setBorderStyle(BorderStyle.DOTTED);
		painelHorizontal.setWidth("auto");
		painelHorizontal.setSpacing(10);
		
		botaoProcurar.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				obterContato(textoBuscar.getText());		
			}
		});
		
		botatoTodosContatos.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				atualizarTabela(null);	
			}
		});

		painelHorizontal.add(labelNomeContato);
		painelHorizontal.add(textoBuscar);
		painelHorizontal.add(botaoProcurar);
		painelHorizontal.add(botatoTodosContatos);
		
		painelHorizontal.setCellVerticalAlignment(labelNomeContato, VerticalPanel.ALIGN_MIDDLE);
			
		return painelHorizontal;
		
	}
	
	private CellTable<Contato> criarTabela(){
		listaContato = new ArrayList<Contato>(); 

		tabelaContatos = new CellTable<Contato>();
		
		tabelaContatos.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);

		TextColumn<Contato> nome = new TextColumn<Contato>() {
			@Override
			public String getValue(Contato contato) {
				return contato.getNome();
			}
		};

		TextColumn<Contato> email = new TextColumn<Contato>() {
			@Override
			public String getValue(Contato contato) {
				return contato.getEmail();
			}
		};

		TextColumn<Contato> categoria = new TextColumn<Contato>() {
			@Override
			public String getValue(Contato contato) {
				return contato.getCategoria();
			}
		};

		TextColumn<Contato> telefone = new TextColumn<Contato>() {
			@Override
			public String getValue(Contato contato) {
				return contato.getTelefone();
			}
		};

		ButtonCell deletarBotao = new ButtonCell();
		Column<Contato, String> delete = new Column<Contato, String>(
				deletarBotao) {
			@Override
			public String getValue(Contato c) {
				return "Deletar";
			}
		};
		delete.setFieldUpdater(new FieldUpdater<Contato, String>() {
			@Override
			public void update(int index, Contato contato, String valor) {
				final Contato contatoRemocao = contato;
				final DialogBox dialogBox = criarCaixaDialogo(contatoRemocao);
			    dialogBox.setGlassEnabled(true);
			    dialogBox.setAnimationEnabled(true);
				
				dialogBox.center();
	            dialogBox.show();
				
			}
		});

		final SingleSelectionModel<Contato> selectionModel = new SingleSelectionModel<Contato>();
	    tabelaContatos.setSelectionModel(selectionModel);
	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
	      public void onSelectionChange(SelectionChangeEvent event) {
	        Contato newContato = selectionModel.getSelectedObject();
	        if (newContato != null) {
	        	textoNome.setEnabled(false);
	        	textoNome.setText(newContato.getNome());
				textoTelefone.setText(newContato.getTelefone());
				textoEmail.setText(newContato.getEmail());
				textoCategoria.setText(newContato.getCategoria());
	        }
	      }
	    });

		tabelaContatos.addColumn(nome, "Nome");
		tabelaContatos.addColumn(email, "E-mail");
		tabelaContatos.addColumn(categoria, "Categoria");
		tabelaContatos.addColumn(telefone, "Telefone");
		tabelaContatos.addColumn(delete, "Deletar");
		
		tabelaContatos.setWidth("100%");

		atualizarTabela(null);

		return tabelaContatos;
	}
	
	private VerticalPanel criarCampoInserirAtualizar() {
		VerticalPanel painelVertical = new VerticalPanel();
		HorizontalPanel painelHorizontal = new HorizontalPanel();
		painelHorizontal.setSpacing(7);
		painelVertical.setWidth("100%");
		painelVertical.setTitle("Criar/Atualizar contato");
		
		Label labelNome = new Label("Nome:");
		Label labelEmail = new Label("Email:");
		Label labelTelefone = new Label("Telefone:");
		Label labelCategoria = new Label("Categoria:");
		Button botaoAdicionar = new Button("Criar");
		Button botaoEditar = new Button("Editar");	
		Button botaoLimpar = new Button("Limpar");
		
		//eventos dos botões
		botaoAdicionar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				inserir();				
			}
		});
		
		botaoEditar.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				editar();
			}
		});
		
		botaoLimpar.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				limparDados();
			}
		});
		
		painelHorizontal.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		painelHorizontal.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		painelVertical.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		painelVertical.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		
		textoNome = new TextBox();
		textoEmail = new TextBox();
		textoTelefone = new TextBox();
		textoCategoria = new TextBox();
		
		painelVertical.add(labelNome);
		painelVertical.add(textoNome);
		painelVertical.add(labelEmail);
		painelVertical.add(textoEmail);
		painelVertical.add(labelTelefone);
		painelVertical.add(textoTelefone);
		painelVertical.add(labelCategoria);
		painelVertical.add(textoCategoria);
		
		
		painelHorizontal.add(botaoAdicionar);
		painelHorizontal.add(botaoEditar);
		painelHorizontal.add(botaoLimpar);
		
		painelVertical.add(painelHorizontal);
		
		return painelVertical;
	}
	
	private DialogBox criarCaixaDialogo(final Contato contato) { 
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Deletar");

		// Create a table to layout the content
		VerticalPanel conteudoDialogo = new VerticalPanel();
		HorizontalPanel tituloDialogo = new HorizontalPanel();
		HorizontalPanel botoes = new HorizontalPanel();
		botoes.setSpacing(10);
		botoes.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		
		Label titulo = new Label("Deseja deletar o contato: " + contato.getNome() + " ?");
		tituloDialogo.add(titulo);
		
		conteudoDialogo.setSpacing(4);
		dialogBox.setWidget(conteudoDialogo);

		// Add a close button at the bottom of the dialog
		Button botaoCancelar = new Button("Cancelar", new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});

		Button botaoDeletar = new Button("Deletar", new ClickHandler() {
			public void onClick(ClickEvent event) {
				service.remover(contato, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(Void result) {
						atualizarTabela(null);
						limparDados();
						dialogBox.hide();
					}
				});
			}
		});
		botoes.add(botaoCancelar);
		botoes.add(botaoDeletar);
	    
		conteudoDialogo.add(tituloDialogo);
		conteudoDialogo.add(botoes);
		
		conteudoDialogo.setCellHorizontalAlignment(botoes, HorizontalPanel.ALIGN_CENTER);
	    // Return the dialog box
	    return dialogBox;
	}
	
	private void atualizarTabela(final Contato contatoEncontrado) {
		dataProvider = new AsyncDataProvider<Contato>() {
			@Override
			protected void onRangeChanged(HasData<Contato> display) {
					start = display.getVisibleRange().getStart();
					length = display.getVisibleRange().getLength();
				
				// the async call 
				asyncContato = new AsyncCallback<ArrayList<Contato>>() {
					@Override
					public void onFailure(Throwable caught) {
						
					}
					@Override
					public void onSuccess(ArrayList<Contato> result) {
						listaContato = result;
						if(contatoEncontrado != null) {
							//limpa a lista e adiciona o contato encontrado, para apenas exibi-lo
							listaContato.clear();
							listaContato.add(contatoEncontrado);
							start = 0;
						}
						//para evitar o "layout" de carregamento infinito realizar a verificao de opcao == 1
						updateRowCount(listaContato.size() + start, true);
						updateRowData(start, listaContato);
					}
				};
				// make the call
				service.listar(asyncContato);
			}
		};
		// link the data provider to the table
		dataProvider.addDataDisplay(tabelaContatos);
	}
	
	private void limparDados() {
		textoNome.setEnabled(true);
		textoCategoria.setText("");
		textoEmail.setText("");
		textoNome.setText("");
		textoTelefone.setText("");
	}
	
	
	private void inserir(){
		ValidaDados validaDados = new ValidaDados();
		boolean dadoOk = validaDados.verificarDados(textoNome.getText(), textoEmail.getText(), 
													textoTelefone.getText(), textoCategoria.getText());
		if(dadoOk) {
			Contato novoContato = new Contato(textoNome.getText(), textoEmail.getText(), textoCategoria.getText(), textoTelefone.getText());
			service.inserir(novoContato, new AsyncCallback<Void>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Não foi possível salvar os dados devido ao erro: " + caught.getMessage() + " no servidor");
				}
				
				@Override
				public void onSuccess(Void result) {
					Window.alert("Dados salvos com sucesso!");
					limparDados();
					atualizarTabela(null);
				}
			});
		} else {
			Window.alert(new StringBuilder().append("Verifique se:\n1 - Todos os campos est\u00e3o preenchidos\n2 - O campo nome tem apenas letras\n")
					.append("3 - O campo e-mail esta no formato: xxxx@xxx.com.xx\n4 - O campo telefone possui apenas n\u00fameros\n").toString());
		}
	}
	
	private void editar(){
		ValidaDados validaDados = new ValidaDados();
		boolean dadoOk = validaDados.verificarDados(textoNome.getText(), textoEmail.getText(), 
													textoTelefone.getText(), textoCategoria.getText());
		if(dadoOk) {
			Contato novoContato = new Contato(textoNome.getText(), textoEmail.getText(), textoCategoria.getText(), textoTelefone.getText());
			service.atualizar(novoContato, new AsyncCallback<Void>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Não foi possível atualizar os dados devido ao erro: " + caught.getMessage() + " no servidor");
				}
				
				@Override
				public void onSuccess(Void result) {
					Window.alert("Dados atualizados com sucesso!");
					limparDados();
					atualizarTabela(null);
				}
			});
		} else {
			Window.alert("Todos os dados devem ser preenchidos");
		}
	}
	
	private void obterContato(final String nome) {
		service.obter(nome, new AsyncCallback<Contato>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Nenhum contato com o nome de: " + nome + " foi encotrado!");
			}

			@Override
			public void onSuccess(Contato result) {
				if(result == null) {
					//mostra uma mensagem informando que nenum contato foi encontrado e mostra todos os contatos existentes.
					Window.alert("Nenhum contato com o nome de: " + nome + " foi encotrado!");
				}
				
				atualizarTabela(result);
			}
		});
	}
	
	
	
}
